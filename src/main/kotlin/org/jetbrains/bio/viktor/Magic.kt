package org.jetbrains.bio.viktor

/**
 * A special object used to denote all indices.
 *
 * @since 0.1.1 Renamed to `_I` because all-underscore names are reserved
 *              for internal use in Kotlin.
 */
object _I {}

/**
 * Converts a multi-dimensional index to an index into the flattened array.
 *
 * @since 0.4.0
 * @see unravelIndex
 */
fun ravelIndex(indices: IntArray, shape: IntArray): Int {
    var index = 0
    var stride = 1
    for (i in shape.indices.reversed()) {
        index += indices[i] * stride
        stride *= shape[i]
    }

    return index
}

/**
 * The inverse of [ravelIndex].
 *
 * @since 0.4.0
 */
fun unravelIndex(index: Int, shape: IntArray): IntArray {
    var remaining = index
    val indices = IntArray(shape.size)
    for (i in shape.indices.reversed()) {
        indices[i] = remaining % shape[i]
        remaining = (remaining / shape[i]) * shape[i]
    }

    return indices
}