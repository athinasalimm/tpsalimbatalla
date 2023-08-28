module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ = Qua 

capacityQ :: Quality -> Int
capacityQ (Qua _ amount_tunels _) | amount_tunels > 0 = amount_tunels
                                  | otherwise = error "La capacidad no puede ser nula"

delayQ :: Quality -> Float
delayQ (Qua _ _ delay) | delay > 0 = delay
                       | otherwise = error "La demora es negativa o nula, esto no es posible"
