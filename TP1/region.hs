module Region ( Region, foundR, newR, linkR, connectedR, linkedR, tunelR, delayR, availableCapacityForR)
   where
import City
import Quality
import Link
import Point
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunels) city | city `elem` cities || coincidenceP city cities = error "La ciudad ya esta en la region, o ya existe una en ese punto"
                                      | otherwise = Reg (city:cities) links tunels

coincidenceP :: City -> [City] -> Bool
coincidenceP city [] = False
coincidenceP city (c:cs) | distanceC city c == 0 = True
                         | otherwise = coincidenceP city cs

foundL :: Region -> Link -> Region
foundL (Reg cities links tunels) link | link `elem` links = error "Las ciudades ya estan enlazadas con un link de esa calidad"
                                      | otherwise = Reg cities (link : links) tunels

linkR :: Region -> City -> City -> Quality -> Region 
linkR region city1 city2 quality = foundL region (newL city1 city2 quality)

checksC :: [City] -> [City] -> Bool 
checksC (c:cs) [] = True
checksC [] (c1:cs1) = error "La region no tiene ciudades"
checksC (c:cs) (c1:cs1) |c1 `elem` (c:cs) = checksC (c:cs) cs1
                        |otherwise = False

cityLinking :: [City] -> [Link] -> Bool 
cityLinking [c] _ = True
cityLinking [] (l:ls) = True
cityLinking (c:cs) [] = error "No hay links en la region que permitan esto"
cityLinking (c:cs) (l:ls) | linksL c (head cs) l = cityLinking cs ls
                          | otherwise = False

joinL :: [City] -> [Link] -> [Tunel] -> [Link]
joinL (c:cs) [] tuneles = []
joinL [] _ tuneles = []
joinL (c:cs) (l:ls) tuneles| linksL c (head cs) l && countCapacity l tuneles > 0 = l : joinL cs ls tuneles
                           | otherwise = joinL (c:cs) ls tuneles

tunelR :: Region -> [ City ] -> Region 
tunelR (Reg cities links tunels) cities1 |checksC cities cities1 && cityLinking cities1 links && not (connectedR (Reg cities links tunels) (head cities1) (last cities1))  = Reg cities links (tunels ++ [newT (joinL cities1 links tunels)])
                                         |otherwise = Reg cities links tunels

connectedR :: Region -> City -> City -> Bool 
connectedR (Reg _ _ []) city1 city2 = False
connectedR (Reg cities links (t:ts)) city1 city2 | connectsT city1 city2 t = True
                                                 | otherwise = connectedR (Reg cities links ts) city1 city2

linkedR :: Region -> City -> City -> Bool 
linkedR (Reg _ [] _) city1 city2 = False
linkedR (Reg cities (l:ls) tunels) city1 city2 | linksL city1 city2 l = True
                                               | otherwise = linkedR (Reg cities ls tunels) city1 city2

delayR :: Region -> City -> City -> Float 
delayR (Reg cities links []) city1 city2 = error "Las ciudades no estan conectadas"
delayR (Reg cities links (t:ts)) city1 city2 | connectsT city1 city2 t = delayT t
                                             | otherwise = delayR (Reg cities links ts) city1 city2

findL :: [Link] -> City -> City -> Link
findL [] city1 city2 = error "No existe link que las conecte"
findL (l:ls) city1 city2 | linksL city1 city2 l = l
                         | otherwise = findL ls city1 city2

countT :: [Tunel] -> Link -> Int
countT [] _ = 0
countT (t:ts) link | usesT link t = 1 + countT ts link
                   | otherwise = countT ts link

countCapacity :: Link -> [Tunel] -> Int
countCapacity link tunels | (capacityL link - countT tunels link) > 0 = capacityL link - countT tunels link
                          | otherwise = error "Ya no le queda capacidad a este link"

availableCapacityForR :: Region -> City -> City -> Int 
availableCapacityForR (Reg cities [] tunels) city1 city2 = error "No hay links que enlacen estas ciudades"
availableCapacityForR (Reg cities links tunels) city1 city2 | linkedR (Reg cities links tunels) city1 city2 = countCapacity (findL links city1 city2) tunels
                                                            | otherwise = error "No hay links que enlacen estas ciudades"
