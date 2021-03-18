package additionalTask

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.io.File

internal class ChessKtTest {

    @Test
    fun phoneBook() {
        val actualList = nameByPhone(4826)
//        println(actualList)
        val expectedList = listOf<String>("Ivan", "Hubo")
//        println(expectedList)
        assertEquals(actualList, expectedList)
    }

    @Test
    fun coordinates() {
        val test = charSequence("42 44 57 67 77 53 33 23 43 78")
    }
}


//
//    @Test
//    fun pathFindSuccess() {
//        val expectedList = mutableListOf<Coordinate>(
//            Coordinate(2, 6),
//            Coordinate(3, 8),
//            Coordinate(4, 6),
//            Coordinate(2, 7),
//            Coordinate(4, 8),
//            Coordinate(6, 7),
//            Coordinate(7, 5),
//            Coordinate(6, 3)
//
//        )
//        val actualList = main("input/Chess.txt", 9)
//        assertEquals(actualList, expectedList)
//        //println(actualList)
//
//    }
//
//    @Test
//    fun pathFindFail() {
//        val exception = assertThrows(RuntimeException::class.java) {
//            main("input/Chess.txt", 5)
//        }
//        assertEquals("The number of moves exceeds the limit 5", exception.message)
//    }
//
//    @Test
//    fun testValidatePawnsDataFail() {
//        val exception = assertThrows(IllegalArgumentException::class.java) {
//            main("input/ChessTestValidatePawnsInputDataFail.txt", 0)
//        }
//        assertEquals("File should contains > 1 \'p\' char", exception.message)
//    }
//
//    @Test
//    fun testValidateHorseDataFail() {
//        val exception = assertThrows(IllegalArgumentException::class.java) {
//            main("input/ChessTestValidateHorseInputDataFail.txt", 0)
//        }
//        assertEquals("File should contains 1 'N' char", exception.message)
//    }
//
//    @Test
//    fun testGetCoordinates() {
//        val coordinates = getCoordinates(File("input/ChessTestValidateHorseInputDataFail.txt"), 9, 'p')
//        val expectedCoordinates = mutableListOf<Coordinate>(
//            Coordinate(2, 7),
//            Coordinate(5, 5),
//            Coordinate(6, 3)
//        )
//        assertEquals(coordinates, expectedCoordinates)
//    }
//
//}