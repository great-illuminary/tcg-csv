package eu.codlab.tcg.pricing

import eu.codlab.files.VirtualFile
import eu.codlab.tcg.pricing.models.Category
import eu.codlab.tcg.pricing.models.Group
import eu.codlab.tcg.pricing.models.Price
import eu.codlab.tcg.pricing.models.Price2
import eu.codlab.tcg.pricing.models.PriceMonthly
import eu.codlab.tcg.pricing.requests.RequestLoader
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.time.Duration.Companion.minutes

class TestDownload {

    private val json = Json
    private val json2 = Json {
        explicitNulls = false
    }

    @Test
    fun testLoadingEverything() = runTest(timeout = 380.minutes) {
        val categories = RequestLoader.categories()

        val folder = VirtualFile(VirtualFile.Root, "cached")
        val folder2 = VirtualFile(VirtualFile.Root, "cached2")
        val folderReduced = VirtualFile(VirtualFile.Root, "cachedReduced")
        val monthlyReduced = VirtualFile(VirtualFile.Root, "monthlyReduced")
        val monthlyList = VirtualFile(VirtualFile.Root, "monthlyList")

        listOf(folder, folder2, folderReduced, monthlyReduced, monthlyList).forEach { it.mkdirs() }

        var categoryGroups: MutableList<Pair<Category, List<Group>>> = mutableListOf()
        categories.forEach { category ->
            try {
                val groups = RequestLoader.groups(category.categoryId)

                categoryGroups.add(category to groups)
            } catch (err: IllegalStateException) {
                println("couldn't load ${err.message}")
            }
        }

        val total = categoryGroups.map { it.second.size }.sum()

        var i = 0
        categoryGroups.forEachIndexed { index, (category, groups) ->
            if (category.categoryId == 69 || category.categoryId == 70) return@forEachIndexed

            groups.forEachIndexed { index2, group ->
                try {
                    val prices = RequestLoader.prices(category.categoryId, group.groupId)
                    val prices2 = prices.map { it.toPrice2() }

                    println("${i}/${total} ${category.categoryId} ${group.groupId} ${prices.size}")

                    val file = VirtualFile(folder, "${index}_${index2}_pricing.json")
                    val file2 = VirtualFile(folder2, "${index}_${index2}_pricing.json")
                    val fileReduced = VirtualFile(folderReduced, "${index}_${index2}_pricing.json")

                    file.write(
                        json.encodeToString(ListSerializer(Price.serializer()), prices)
                            .encodeToByteArray()
                    )

                    file2.write(
                        json.encodeToString(ListSerializer(Price.serializer()), prices)
                            .encodeToByteArray()
                    )

                    fileReduced.write(
                        json2.encodeToString(ListSerializer(Price2.serializer()), prices2)
                            .encodeToByteArray()
                    )

                    (0..30).forEach {
                        val fileNonList =
                            VirtualFile(monthlyReduced, "${index}_${index2}_pricing_${it}.json")

                        fileNonList.write(
                            json2.encodeToString(ListSerializer(Price2.serializer()), prices2)
                                .encodeToByteArray()
                        )
                    }

                    val fileInList = VirtualFile(monthlyList, "${index}_${index2}_pricing.json")

                    fileInList.write(
                        json2.encodeToString(
                            ListSerializer(PriceMonthly.serializer()),
                            prices2.map {
                                it.toMonthly()
                            }
                        ).encodeToByteArray()
                    )

                    i++
                } catch (err: Throwable) {
                    println("issue with ${err.message}")
                }
            }
        }
    }
}