package com.indix.bootcamp.parser

import org.scalatest.{Matchers, FunSuite}
import com.indix.bootcamp.utils.TestUtils

class JabongParserTest extends FunSuite with Matchers with TestUtils {

  test("should parse product page") {
    val document = readDocument("/flipkart/jabong_3.html")
    val parser = new JabongParser
    val parsedProduct = parser.parseProduct(document, "www.jabong.com/celio-100-Linen-Casual-Shirt-551778.html")
    parsedProduct.name should be("Celio 100% Linen Casual Shirt")
    parsedProduct.description should include("Parade the retro style when you adorn this aqua blue coloured casual shirt by Celio. Moisture-wicking design and lightweight" +
      " design makes this regular fit casual shirt a must-have in your wardrobe. Style it with contrast colour chinos for an event that doesn't demand formals.")
  }

  test("should parse prices from product page") {
    val document = readDocument("/flipkart/jabong_3.html")
    val parser = new JabongParser
    val parsedPrice = parser.parsePrice(document)
    println(parsedPrice.salePrice)
    println(parsedPrice.listPrice)
    parsedPrice.listPrice should be(2699.0)
    parsedPrice.salePrice should be(2199.0)
  }

 }