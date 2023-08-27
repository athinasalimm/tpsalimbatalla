module Link ( Link, newL, linksL, connectsL, delayL, capacityL) 
where
import Point
import Quality
import City
data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link
newL = Lin

connectsL :: Eq City => City -> Link -> Bool  
connectsL city (Lin city1 city2 _) | city == city1 || city == city2 = True
                                   | otherwise = False

linksL :: City -> City -> Link -> Bool
linksL city1 city2 link | connectsL city1 link && connectsL city2 link = True
                        | otherwise = False

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality 

delayL :: Link -> Float 
delayL (Lin city1 city2 quality) = distanceC city1 city2 / delayQ quality
