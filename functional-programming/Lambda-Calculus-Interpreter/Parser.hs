{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}

{-# HLINT ignore "Use camelCase" #-}
{-# HLINT ignore "Use lambda-case" #-}
module Parser where

import Control.Applicative
import Control.Monad
import Data.Char (isAlpha, isLower)
import Expr

-- Parser data type
newtype Parser a = Parser
  { parse :: String -> Maybe (a, String)
  }

--- type declaration ---

instance Monad Parser where
  return a = Parser $ \s -> Just (a, s)
  (>>=) (Parser p) faPb = Parser $ \s -> case p s of
    Nothing -> Nothing
    Just (a, st) -> parse (faPb a) st

instance Applicative Parser where
  pure = return
  pf <*> px = do
    f <- pf
    x <- px
    return $ f x

instance Functor Parser where
  fmap f px = do
    x <- px
    return $ f x

instance Alternative Parser where
  empty = Parser $ const Nothing
  p1 <|> p2 = Parser $ \s -> case parse p1 s of
    Nothing -> parse p2 s
    ok -> ok

--- type declaration over ---

{-
Parses multiple characters that satisfy the predicate
-}
predParser :: (Char -> Bool) -> Parser Char
predParser p = Parser $ \s -> case s of
  [] -> Nothing
  (x : xs) -> if p x then Just (x, xs) else Nothing

{-
Parse at least one time
-}
plusParser :: Parser a -> Parser [a]
plusParser p = do
  x <- p
  xs <- starParser p
  return (x : xs)

{-
Parse zero or more times
-}
starParser :: Parser a -> Parser [a]
starParser p = plusParser p <|> return []

{-
Parses varible names as strings
-}
varParser :: Parser String
varParser = plusParser (predParser (\c -> isAlpha c && isLower c))

{-
Creates a Variable String from parsing
-}
varExprParser :: Parser Expr
varExprParser = Variable <$> varParser

spaceParser :: Parser Char
spaceParser = predParser (== ' ')

{-
Creates function that can have everything in its body
-}
funcExprParser :: Parser Expr
funcExprParser = do
  predParser (== '\\')
  x <- varParser
  predParser (== '.')
  Function x <$> atomicParser

{-
Parser for application that are closed by parenthesis.
-}
closedApplParser :: Parser Expr
closedApplParser = do
  predParser (== '(')
  e1 <- exprParser
  predParser (== ')')
  return e1

{-
Parser that fails if the next symbol is not $.
It returns if a Macro can be created.
-}
macroParser :: Parser Expr
macroParser = do
  predParser (== '$')
  Macro <$> varParser

{-
Parser that can create every intermediat expr
-}
atomicParser :: Parser Expr
atomicParser = closedApplParser <|> funcExprParser <|> macroParser <|> varExprParser

{-
Recusive function that on failure to parse another expr after a space.
Returns application created so far or if there are no spaces returns the expr.
-}
exprParser :: Parser Expr
exprParser = do
  e1 <- atomicParser
  spaceParser *> auxApplParse e1 <|> return e1
  where
    auxApplParse :: Expr -> Parser Expr
    auxApplParse ex = do
      e2 <- atomicParser
      spaceParser *> auxApplParse (Application ex e2) <|> return (Application ex e2)

{-
Reutrns the parsed expresions on success.
Else throws error
-}
parse_expr :: String -> Expr
parse_expr s = case parse exprParser s of
  Just (x, s) -> x
  Nothing -> error "Cannot parse expr"


assignParser :: Parser Code
assignParser = do
  x <- varParser
  starParser (spaceParser)
  predParser (== '=')
  starParser (spaceParser)
  Assign x <$> exprParser

evalParser :: Parser Code
evalParser = do
  Evaluate <$> exprParser

{-
Reutrns the parsed code on success.
Else throws error
-}
parse_code :: String -> Code
parse_code s = case parse (assignParser <|> evalParser) s of
  Just (x, st) -> x
  Nothing -> error "Cannot parse code"
