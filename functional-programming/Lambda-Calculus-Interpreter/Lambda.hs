{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use camelCase" #-}
{-# HLINT ignore "Use second" #-}
{-# HLINT ignore "Use first" #-}

module Lambda where

import Expr
import Data.List

-- TODO 1.1. find free variables of a Expr
free_vars :: Expr -> [String]
free_vars expr =
    case expr of
        Variable x -> [x]
        Function x e -> filter (x /= ) (free_vars e)
        Application e1 e2 -> free_vars e1 `union` free_vars e2


create_new_name :: [String] -> String
create_new_name lNames = show (head (dropWhile (\x -> show x `elem` lNames) [1..]))


-- TODO 1.2. reduce a redex
reduce :: Expr -> String -> Expr -> Expr
reduce exp1 x exp2 =
    case exp1 of
        Variable m -> if m == x then exp2 else exp1
        Application e1 e2 -> a (reduce e1 x exp2) (reduce e2 x exp2)
        Function y e -> if y == x then exp1
                        else if y `notElem` free_v_exp2 then f y (reduce e x exp2)
                        else reduce (f new_var (reduce e y (v new_var))) x exp2
        where
            free_v_exp2 = free_vars exp2
            new_var = create_new_name free_v_exp2


reduceAllX :: (Expr -> Expr) -> Expr -> [Expr]
reduceAllX func expr =  if expr == next_step then [expr]
                else expr : reduceAllX func next_step
                where next_step = func expr

-- Normal Evaluation
-- TODO 1.3. perform one step of Normal Evaluation
stepN :: Expr -> Expr
stepN expr = case expr of
    Application e1 e2 ->  case e1 of
        Function x e -> reduce e x e2
        Application _ _ -> a (stepN e1) e2
        Variable _ -> a e1 (stepN e2)
    Function x e -> f x (stepN e)
    Variable x -> expr

-- TODO 1.4. perform Normal Evaluation
reduceN :: Expr -> Expr
reduceN = last . reduceAllN

reduceAllN :: Expr -> [Expr]
reduceAllN = reduceAllX stepN

-- Applicative Evaluation
-- TODO 1.5. perform one step of Applicative Evaluation
stepA :: Expr -> Expr
stepA expr = case expr of
    Application e1 e2 -> case (e1, e2) of
        (Application _ _, _) -> a (stepA e1) e2
        (_ , Application _ _) -> a e1 (stepA e2)
        (Function x e , _) -> if e /= stepA e then a (stepA e1) e2
                            else if stepA e2 /= e2 then a e1 (stepA e2)
                            else reduce e x e2
        (Variable x , _) -> a e1 (stepA e2)
    Function x e -> f x (stepA e)
    _ -> expr


-- TODO 1.6. perform Applicative Evaluation
reduceA :: Expr -> Expr
reduceA = last . reduceAllA

reduceAllA :: Expr -> [Expr]
reduceAllA = reduceAllX stepA

-- TODO 3.1. make substitutions into a expression with Macros
evalMacros :: [(String, Expr)] -> Expr -> Expr
evalMacros dic exp = case exp of
    Macro x -> case lookup x dic of
                Nothing -> error "Macro is not in the env"
                Just xv -> eM xv
    Application e1 e2 -> a (eM e1) (eM e2)
    Function x e1 -> f x (eM e1)
    _ -> exp
    where
        eM :: Expr -> Expr
        eM = evalMacros dic

-- TODO 4.1. evaluate code sequence using given strategy
evalCode :: (Expr -> Expr) -> [Code] -> [Expr]
-- evalCode startegy code_list = fmap (startegy . evalMacros dict) to_eval
--     where
--         dict = [(s, e)| Assign s e <- code_list]
--         to_eval = [e | Evaluate e <- code_list]



evalCode startegy cl = reverse (snd (foldl aux ([], []) cl))
    where
        aux :: ([(String, Expr)], [Expr]) -> Code -> ([(String, Expr)], [Expr])
        aux acc (Assign x e) = case lookup x (fst acc) of
            Nothing -> ((x,e) : fst acc, snd acc)
            Just v -> ((x,e) : delete (x,v) (fst acc), snd acc)
        aux acc (Evaluate e) = (fst acc, startegy (evalMacros (fst acc) e) : snd acc)