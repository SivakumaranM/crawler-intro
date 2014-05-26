package com.indix.bootcamp.models

case class Product(name: String, description: String, url: String) {
  def toCsv = List(name, description, url).mkString(";")
}

case class Price(listPrice: Double, salePrice: Double) {
  def toCsv = List(listPrice, salePrice).mkString(";")
}

case class Result(product: Option[Product], price: Option[Price]) {
  def isValidProductPage = product.isDefined && price.isDefined

  def toCsv = s"${product.map(_.toCsv).getOrElse(",,")};${price.map(_.toCsv).getOrElse(",")}"
}
