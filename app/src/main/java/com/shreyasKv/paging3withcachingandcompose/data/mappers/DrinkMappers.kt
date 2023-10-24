package com.shreyasKv.paging3withcachingandcompose.data.mappers

import com.shreyasKv.paging3withcachingandcompose.data.local.DrinkEntity
import com.shreyasKv.paging3withcachingandcompose.data.remote.DrinkDto
import com.shreyasKv.paging3withcachingandcompose.domain.Drink

fun DrinkDto.toDrinkEntity(): DrinkEntity {
    return DrinkEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun DrinkEntity.toDrink(): Drink {
    return Drink(
        id = id,
        name = name,
        tagLine = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}