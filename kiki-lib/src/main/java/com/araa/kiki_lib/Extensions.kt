package com.araa.kiki_lib

import com.araa.kiki_lib.model.ItemPackage
import java.text.DecimalFormat

fun List<List<ItemPackage>>.sortByDecendingOrder(): List<List<ItemPackage>> {
    val weightSumsMap = mutableMapOf<Int, Double>()
    this.forEachIndexed { index, packageList ->
        val sum = packageList.sumOf { it.weight }
        weightSumsMap[index] = sum
    }
    // Sort the map by the sums of weights in descending order
    val sortedWeightSumsMap = weightSumsMap.toList().sortedByDescending { (_, value) -> value }.toMap()
    // Reconstruct the sorted 2D list
    val sortedItemPackages = mutableListOf<List<ItemPackage>>()
    sortedWeightSumsMap.forEach { (index, _) ->
        sortedItemPackages.add(this[index])
    }
    return sortedItemPackages
}

fun Double.roundToTwoDecimal() : Double{
    val df = DecimalFormat("#.##")
    return df.format(this).toDouble()
}