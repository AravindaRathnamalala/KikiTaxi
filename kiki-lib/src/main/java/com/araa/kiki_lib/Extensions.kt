package com.araa.kiki_lib

import com.araa.kiki_lib.model.ItemPackage

fun List<List<ItemPackage>>.sortByDecendingOrder(): List<List<ItemPackage>> {
    val weightSumsMap = mutableMapOf<Int, Double>()
    this.forEachIndexed { index, packageList ->
        val sum = packageList.sumOf { it.weight }
        weightSumsMap[index] = sum
    }
    // Sort the map by the sums of weights in descending order
    val sortedWeightSumsMap = weightSumsMap.toList().sortedByDescending { (_, value) -> value }.toMap()
    // Reconstruct the sorted 2D list
    val sortedItemPackages2D = mutableListOf<List<ItemPackage>>()
    sortedWeightSumsMap.forEach { (index, _) ->
        sortedItemPackages2D.add(this[index])
    }
    return sortedItemPackages2D
}