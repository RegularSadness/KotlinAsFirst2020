package additionalTask

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class ChessKtTest {

    @Test
    fun pathFindSuccess() {
        val expectedList = mutableListOf<Coordinate>(
            Coordinate(6, 3),
            Coordinate(7, 5),
            Coordinate(6, 7),
            Coordinate(4, 8),
            Coordinate(2, 7),
            Coordinate(4, 6),
            Coordinate(3, 8),
            Coordinate(2, 6)
        )
        val actualList = main("input/Chess.txt", 9)
        assertEquals(actualList, expectedList)

    }

    @Test
    fun pathFindFail() {
        val exception = assertThrows(RuntimeException::class.java) {
            val resultPass = main("input/Chess.txt", 5)
            println(resultPass)
        }
        assertEquals("The number of moves exceeds the limit 5", exception.message)
    }
}