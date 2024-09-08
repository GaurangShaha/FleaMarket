package com.flea.market.product.data.input

import com.flea.market.product.data.remote.entity.ProductDetailsEntity
import com.flea.market.product.data.remote.entity.RatingEntity

@Suppress("MaxLineLength")
internal val productDetailsEntityList = listOf(
    ProductDetailsEntity(
        id = 1,
        title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        price = 109.95,
        description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
        category = "men's clothing",
        image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        rating = RatingEntity(
            rate = 3.9,
            count = 120
        ),
        imageList = listOf("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
    ),
    ProductDetailsEntity(
        id = 5,
        title = "John Hardy Women's Legends Naga Gold & Silver Dragon Station Chain Bracelet",
        price = 695.00,
        description = "From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.",
        category = "jewelery",
        image = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg",
        rating = RatingEntity(
            rate = 4.6,
            count = 400
        ),
        imageList = listOf("https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg")
    ),
    ProductDetailsEntity(
        id = 9,
        title = "WD 2TB Elements Portable External Hard Drive - USB 3.0 ",
        price = 64.00,
        description = "USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on user’s hardware configuration and operating system",
        category = "Electronics",
        image = "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg",
        rating = RatingEntity(
            rate = 3.3,
            count = 203
        ),
        imageList = listOf("https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg")
    ),
    ProductDetailsEntity(
        id = 16,
        title = "Lock and Love Women's Removable Hooded Faux Leather Moto Biker Jacket",
        price = 29.95,
        description = "100% POLYURETHANE(shell) 100% POLYESTER(lining) 75% POLYESTER 25% COTTON (SWEATER), Faux leather material for style and comfort / 2 pockets of front, 2-For-One Hooded denim style faux leather jacket, Button detail on waist / Detail stitching at sides, HAND WASH ONLY / DO NOT BLEACH / LINE DRY / DO NOT IRON",
        category = "women's clothing",
        image = "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg",
        rating = RatingEntity(
            rate = 2.9,
            count = 340
        ),
        imageList = listOf("https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_.jpg")
    )
)
