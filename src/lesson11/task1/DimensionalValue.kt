@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.lang.IllegalArgumentException

/**
 * Класс "Величина с размерностью".
 *
 * Предназначен для представления величин вроде "6 метров" или "3 килограмма"
 * Общая сложность задания - средняя, общая ценность в баллах -- 18
 * Величины с размерностью можно складывать, вычитать, делить, менять им знак.
 * Их также можно умножать и делить на число.
 *
 * В конструктор передаётся вещественное значение и строковая размерность.
 * Строковая размерность может:
 * - либо строго соответствовать одной из abbreviation класса Dimension (m, g)
 * - либо соответствовать одной из приставок, к которой приписана сама размерность (Km, Kg, mm, mg)
 * - во всех остальных случаях следует бросить IllegalArgumentException
 */
class DimensionalValue(value: Double, dimension: String) : Comparable<DimensionalValue> {
    var originalValue: Double
    var originalDimension: String

    init {
        var splitResult = dimension.split(" ")
        if (splitResult.size == 2) {
            originalValue = splitResult[0].toDouble()
            originalDimension = splitResult[1]
        } else {
            originalDimension = dimension
            originalValue = value
        }
    }

    /**
     * Величина с БАЗОВОЙ размерностью (например для 1.0Kg следует вернуть результат в граммах -- 1000.0)
     */
    val value: Double
        get() {
            for (dimension in Dimension.values()) {
                if (dimension.abbreviation == originalDimension) {
                    return originalValue
                }
            }
            var dimensionWithoutPrefix = originalDimension.substring(1)
            for (dimension in Dimension.values()) {
                if (dimension.abbreviation == dimensionWithoutPrefix) {
                    var prefix = originalDimension.substring(0, 1)
                    var dimensionPrefix = findDimensionPrefix(prefix)
                    return originalValue * dimensionPrefix.multiplier
                }
            }
            throw IllegalArgumentException("Invalid dimension.")
        }

    private fun findDimensionPrefix(prefix: String): DimensionPrefix {
        for (dimPrefix in DimensionPrefix.values()) {
            if (dimPrefix.abbreviation == prefix) {
                return dimPrefix
            }

        }
        throw IllegalArgumentException("Invalid dimension prefix.")
    }

    /**
     * БАЗОВАЯ размерность (опять-таки для 1.0Kg следует вернуть GRAM)
     */
    val dimension: Dimension
        get() {
            for (dimension in Dimension.values()) {
                if (dimension.abbreviation == originalDimension) {
                    return dimension
                }
            }
            var dimensionWithoutPrefix = originalDimension.substring(1)
            for (dimension in Dimension.values()) {
                if (dimension.abbreviation == dimensionWithoutPrefix) {
                    return dimension
                }
            }
            throw IllegalArgumentException("Invalid dimension.")
        }


    /**
     * Конструктор из строки. Формат строки: значение пробел размерность (1 Kg, 3 mm, 100 g и так далее).
     */
    constructor(s: String) : this(0.0, s)

    /**
     * Сложение с другой величиной. Если базовая размерность разная, бросить IllegalArgumentException
     * (нельзя складывать метры и килограммы)
     */
    operator fun plus(other: DimensionalValue): DimensionalValue {
        if (this.dimension == other.dimension) {
            var abbreviation = Dimension.valueOf(this.dimension.toString()).abbreviation
            var plusResult = this.value + other.value
            return DimensionalValue(plusResult, abbreviation)
        }
        throw IllegalArgumentException("Invalid dimension.")
    }

    /**
     * Смена знака величины
     */
    operator fun unaryMinus(): DimensionalValue = DimensionalValue(-originalValue, originalDimension)

    /**
     * Вычитание другой величины. Если базовая размерность разная, бросить IllegalArgumentException
     */
    operator fun minus(other: DimensionalValue): DimensionalValue {
        if (this.dimension == other.dimension) {
            var abbreviation = Dimension.valueOf(this.dimension.toString()).abbreviation
            var minusResult = this.value - other.value
            return DimensionalValue(minusResult, abbreviation)
        }
        throw IllegalArgumentException("Invalid dimension.")
    }

    /**
     * Умножение на число
     */
    operator fun times(other: Double): DimensionalValue = DimensionalValue(originalValue * other, originalDimension)

    /**
     * Деление на число
     */
    operator fun div(other: Double): DimensionalValue = DimensionalValue(originalValue / other, originalDimension)

    /**
     * Деление на другую величину. Если базовая размерность разная, бросить IllegalArgumentException
     */
    operator fun div(other: DimensionalValue): Double {
        if (this.dimension == other.dimension) {
            return value / other.value
        }
        throw IllegalArgumentException("Invalid dimension.")
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DimensionalValue

        if (originalValue != other.originalValue) return false
        if (originalDimension != other.originalDimension) return false

        return true
    }

    override fun hashCode(): Int {
        var result = originalValue.hashCode()
        result = 31 * result + originalDimension.hashCode()
        return result
    }


    /**
     * Сравнение на больше/меньше. Если базовая размерность разная, бросить IllegalArgumentException
     */
    override fun compareTo(other: DimensionalValue): Int {
        if (this.dimension == other.dimension) {
            return value.toInt() - other.value.toInt()
        }
        throw IllegalArgumentException("Invalid dimension.")
    }


}

/**
 * Размерность. В этот класс можно добавлять новые варианты (секунды, амперы, прочие), но нельзя убирать
 */
enum class Dimension(val abbreviation: String) {
    METER("m"),
    GRAM("g");
}

/**
 * Приставка размерности. Опять-таки можно добавить новые варианты (деци-, санти-, мега-, ...), но нельзя убирать
 */
enum class DimensionPrefix(val abbreviation: String, val multiplier: Double) {
    KILO("K", 1000.0),
    MILLI("m", 0.001),
    SANTI("s", 0.01),
    DECI("d", 0.1);
}