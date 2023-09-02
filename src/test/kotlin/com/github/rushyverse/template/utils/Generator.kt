package com.github.rushyverse.template.utils

import org.bukkit.Location
import org.bukkit.World
import kotlin.random.Random

const val LIMIT_RANDOM_COORDINATE = 1000.0

fun randomString(
    allowedChar: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9'),
    size: Int = 50
): String = sequence<Char> { allowedChar.random() }.take(size).joinToString("")

fun randomBoolean(): Boolean = Random.nextBoolean()

fun randomInt(from: Int = Int.MIN_VALUE, until: Int = Int.MAX_VALUE): Int = Random.nextInt(from, until)

fun randomLong(from: Long = Long.MIN_VALUE, until: Long = Long.MAX_VALUE): Long = Random.nextLong(from, until)

fun randomFloat(from: Float = Float.MIN_VALUE, until: Float = Float.MAX_VALUE): Float =
    randomDouble(from.toDouble(), until.toDouble()).toFloat()

fun randomDouble(from: Double = Double.MIN_VALUE, until: Double = Double.MAX_VALUE): Double =
    Random.nextDouble(from, until)

inline fun <reified T : Enum<T>> randomEnum(): T = enumValues<T>().random()

fun randomLocation(
    world: World? = null,
    limit: Double = LIMIT_RANDOM_COORDINATE
): Location {
    val limitFloat = limit.toFloat()
    return Location(
        world,
        randomDouble(0.0, limit),
        randomDouble(0.0, limit),
        randomDouble(0.0, limit),
        randomFloat(0f, limitFloat),
        randomFloat(0f, limitFloat)
    )
}
