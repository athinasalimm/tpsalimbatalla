module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where
import Link
import City
import Point
import Quality

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

linksfromT :: Tunel -> [Link]
linksfromT (Tun links) = links

penultL :: [Link] -> Link 
penultL []       = error "El tunel no presenta links"
penultL[l]      = error "Solo hay un link en este tunel"
penultL [l, _]   = l
penultL (_:ls)   = penultL ls

secondL :: [Link] -> Link
secondL []      = error "El tunel no presenta links"
secondL [l]     = error "Solo hay un link en este tunel"
secondL [_, l] = l
secondL (_:l:_) = l

connectsT :: City -> City -> Tunel -> Bool
connectsT city1 city2 tunel | connectsL city1 (head (linksfromT tunel)) && not (connectsL city1 (secondL (linksfromT tunel)))&& connectsL city2 (last (linksfromT tunel)) && not (connectsL city2 (penultL (linksfromT tunel))) = True
                            | connectsL city2 (head (linksfromT tunel)) && not (connectsL city2 (secondL (linksfromT tunel)))&& connectsL city1 (last (linksfromT tunel)) && not (connectsL city1 (penultL (linksfromT tunel))) = True
                            | otherwise = False

usesT :: Link -> Tunel -> Bool
usesT link tunel | link `elem` linksfromT tunel = True
                 | otherwise = False

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tune
delayT (Tun[]) = 0.0
delayT tunel = delayL (head (linksfromT tunel)) + delayT (newT (tail (linksfromT tunel)))

