{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}

{-# HLINT ignore "Use camelCase" #-}
{-# HLINT ignore "Use lambda-case" #-}
module Parser where

import Control.Applicative
import Control.Monad
import Data.Char (isAlphaNum)
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
  empty = failParser
  p1 <|> p2 = Parser $ \s -> case parse p1 s of
    Nothing -> parse p2 s
    ok -> ok

--- type declaration over ---
failParser :: Parser a
failParser = Parser $ const Nothing

predParser :: (Char -> Bool) -> Parser Char
predParser p = Parser $ \s -> case s of
  [] -> Nothing
  (x : xs) -> if p x then Just (x, xs) else Nothing

plusParser :: Parser a -> Parser [a]
plusParser p =
  do
    x <- p
    xs <- starParser p
    return (x : xs)

starParser :: Parser a -> Parser [a]
starParser p = plusParser p <|> return []

varParser :: Parser String
varParser = do plusParser (predParser isAlphaNum)

varExprParser :: Parser Expr
varExprParser = Variable <$> varParser

funcExprParser :: Parser Expr
funcExprParser = do
  predParser (== '\\')
  x <- varParser
  predParser (== '.')
  e1 <- funcExprParser <|> varExprParser <|> applExprParser
  return (Function x e1)

simpleApplExprParser :: Expr -> Parser Expr
simpleApplExprParser ex = do
  e2 <- funcExprParser <|> applExprParser <|> varExprParser
  predParser (== ' ') *> simpleApplExprParser (Application ex e2) <|> return (Application ex e2) -- problema e ca deja consum un spatiu si cand intra recursiv cade ca spatiul deja e consumat

startApplExprParser :: Parser Expr
startApplExprParser = do
  e1 <- applExprParser <|> funcExprParser <|> varExprParser
  predParser (== ' ') *> simpleApplExprParser e1 <|> return e1

applExprParser :: Parser Expr
applExprParser = do
  predParser (== '(')
  e1 <- startApplExprParser
  predParser (== ')')
  return e1

exprParser :: Parser Expr
exprParser =
  funcExprParser
    <|> startApplExprParser
    <|> applExprParser

-- TODO 2.1. parse a expression
parse_expr :: String -> Expr
parse_expr s = case parse exprParser s of
  Just (x, s) -> x
  Nothing -> error "Cannot parse"

-- TODO 4.2. parse code
parse_code :: String -> Code
parse_code = undefined
