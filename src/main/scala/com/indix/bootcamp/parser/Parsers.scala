package com.indix.bootcamp.parser

import com.indix.bootcamp.models.{Product, Price, Result}
import org.jsoup.nodes.Document

class FlipkartParser extends Parser {
  override def parseProduct(document: Document, pageUrl: String): Product = {
    val title = document.select("h1[itemprop=name]").text()
    val description = document.select("#specifications").text()
    Product(title, description, pageUrl)
  }

  // TODO: Fix the price Extraction
  override def parsePrice(document: Document): Price = {

    val listPrice = document.select(".old-price").text().stripPrefix("Rs.").toDouble
    val salePrice = document.select(".pprice").text().stripPrefix("Rs.").toDouble
    Price(listPrice, salePrice)
   }
}


class JabongParser extends Parser {
  override def parseProduct(document: Document, pageUrl: String): Product = {
    val title = document.select("span[itemprop=brand]").text() + " " + document.select("span[itemprop=name]").text()
    val description = document.select("#productInfo p").text()
    Product(title, description, pageUrl)

  }

  override def parsePrice(document: Document): Price = {
    val salePrice = document.select("span[itemprop=price]").text().toDouble
    val listPrice=salePrice
    if (document.select("span[class=striked-price fs14 c222 d-inline mt5]").text() != "" ) {
      Price(document.select("span[class=striked-price fs14 c222 d-inline mt5]").text().stripPrefix("Rs. ").toDouble, salePrice)
    }
    else {
      Price(listPrice, salePrice)
    }
  }
}

