package com.example.r2dbc.domain

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

class Item(
    @Id var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var price: Double? = null,
    var distributorRegion: String? = null,
    var releaseDate: LocalDateTime = LocalDateTime.now(),
    var availableUnits: Int? = null,
    var active: Boolean? = true
)