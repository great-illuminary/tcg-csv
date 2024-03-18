package eu.codlab.tcg.pricing.presets

/**
 * WIP ! Not sure if I'll keep this one...
 */
@Suppress("MagicNumber")
object Categories {
    val lorcana = Lorcana.entries.toTypedArray()
    val starWarsUnlimited = StarWarsUnlimited.entries.toTypedArray()

    enum class StarWarsUnlimited(
        val categoryId: Int,
        val groupId: Int
    ) {
        SoR(79, 23405),
        Gencon2023(79, 23406),
        WeeklyPlay(79, 23451),
        PrereleasePromos(79, 23452),
        JudgePromos(79, 23453),
        EventExclusivePromos(79, 17690),
        StoreShowdownPromos(79, 23455)
    }

    enum class Lorcana(
        val categoryId: Int,
        val groupId: Int
    ) {
        D23(71, 17690),
        TheFirstChapter(71, 22937),
        DisneyLorcanaPromoCards(71, 23234),
        RiseOfTheFloodBorn(71, 23303),
        D100(71, 23305),
        IntoTheInklands(71, 23367)
    }
}
