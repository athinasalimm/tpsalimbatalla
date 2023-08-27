import Point
import City
import Link
import Quality
import Tunel
import Region
import Control.Exception
import GHC.IO

buenosaires :: City
buenosaires = newC "Buenos Aires" (newP 1 1)

tafiviejo :: City
tafiviejo = newC "Tafi Viejo" (newP 2 1)

laplata :: City
laplata = newC "La Plata" (newP 3 3)

martinez :: City
martinez = newC "Martinez" (newP 6 5)

carilo :: City
carilo = newC "Carilo" (newP 8 7)

sanmiguel :: City
sanmiguel = newC "San Miguel" (newP 9 1)

lanus :: City
lanus = newC "Lanus" (newP 14 6)

alta :: Quality
alta = newQ "alta" 5 0

baja :: Quality
baja = newQ "baja" 0 5.5

media :: Quality
media = newQ "media" 2 2.2

linkbslp :: Link
linkbslp = newL buenosaires laplata alta

linkbstv :: Link
linkbstv = newL buenosaires tafiviejo media

linkmztv :: Link
linkmztv = newL martinez tafiviejo baja

linkclsm :: Link
linkclsm = newL carilo sanmiguel media

linkbscl :: Link
linkbscl = newL buenosaires carilo media

linklpmz :: Link
linklpmz = newL laplata martinez media

tunel1 :: Tunel
tunel1 = newT [linkbslp, linklpmz, linkmztv]

tunel2 :: Tunel
tunel2 = newT [linkbscl, linkclsm]

region1 :: Region
region1 = foundR newR tafiviejo

region11 :: Region
region11 = foundR region1 tafiviejo

region12 :: Region
region12 = foundR region1 buenosaires

region12b :: Region
region12b = foundR region12 lanus

region13 :: Region
region13 = foundR region12b laplata

reg13a :: Region
reg13a = linkR region13 buenosaires tafiviejo media

reg13b :: Region
reg13b = linkR reg13a laplata buenosaires alta

reg13c :: Region
reg13c = linkR reg13b lanus laplata media

region3 :: Region
region3 = tunelR reg13c [lanus, laplata, buenosaires, tafiviejo]

test = [difP (newP 1 1) (newP 2 1) == 1.0, nameC buenosaires == "Buenos Aires", distanceC buenosaires tafiviejo == 1.0,
        capacityQ alta == 5, delayQ baja == 5.5, connectsL tafiviejo linkbslp == False, connectsL buenosaires linkbslp == True,
        linksL buenosaires laplata linkbslp == True, linksL buenosaires tafiviejo linkbslp == False, capacityL linkbslp == 5, delayL linkbstv == 0.45454544,
        connectsT buenosaires tafiviejo tunel1 == True, connectsT buenosaires laplata tunel1 == False, usesT linklpmz tunel1 == True,
        usesT linkbstv tunel1 == False, delayT tunel2 == 6.955594, connectedR region3 tafiviejo lanus == True, availableCapacityForR region3 buenosaires tafiviejo == 1]

testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

test_exceptions = [testF (capacityQ baja), testF (delayQ alta), testF (delayL linkbslp), testF (delayT tunel1),
 testF region11, testF (availableCapacityForR region3 laplata tafiviejo), testF (delayR region3 laplata tafiviejo)]




