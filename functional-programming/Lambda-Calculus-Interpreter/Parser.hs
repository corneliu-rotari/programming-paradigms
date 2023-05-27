{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use camelCase" #-}
{-# HLINT ignore "Use lambda-case" #-}
module Parser  where

import Control.Monad
import Control.Applicative
import Expr
import Data.Char (isAlphaNum)

-- Parser data type
newtype Parser a = Parser {
    parse :: String -> Maybe(a, String)
}

--- type declaration ---

instance Monad Parser where
    return a = Parser $ \s -> Just (a,s)
    (>>=) (Parser p) faPb = Parser $ \s -> case p s of
        Nothing -> Nothing
        Just(a,st) -> parse (faPb a) st


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
                                (x:xs) -> if p x then Just (x,xs) else Nothing

plusParser :: Parser a -> Parser [a]
plusParser p =
  do x <- p
     xs <- starParser p
     return (x:xs)

starParser :: Parser a -> Parser [a]
starParser p = plusParser p <|> return []

varParser :: Parser String
varParser = do starParser (predParser isAlphaNum)

varExprParser :: Parser Expr
varExprParser = Variable <$> varParser

funcExprParser :: Parser Expr
funcExprParser = do
    predParser (=='\\')
    x <- varParser
    predParser (=='.')
    Function x <$> exprParser

simpleApplExprParser :: Parser Expr
simpleApplExprParser = do
    e1 <- varExprParser
    plusParser (predParser (== ' '))
    Application e1 <$> exprParser


applExprParser :: Parser Expr
applExprParser = do
    plusParser (predParser (== '('))
    e1 <- simpleApplExprParser
    plusParser (predParser (== ')'))
    return e1

exprParser :: Parser Expr
exprParser = funcExprParser  <|> simpleApplExprParser <|> applExprParser <|> varExprParser

-- TODO 2.1. parse a expression
parse_expr :: String -> Expr
parse_expr s = case parse exprParser s of
    Just(x, s) -> x

-- parse_expr s = case parse funcExprParser s of
--     Nothing -> v "x"
--     Just(y,x) -> y

-- TODO 4.2. parse code
parse_code :: String -> Code
parse_code = undefined
