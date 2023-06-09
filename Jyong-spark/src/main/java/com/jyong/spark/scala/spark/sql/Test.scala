package com.jyong.spark.scala.spark.sql

import cn.hutool.core.io.FileUtil
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object Test {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("test").master("local").getOrCreate()

    val frame = spark.read.option("delimiter","|").csv("D:\\data\\a.txt")
    frame.show()
    frame.createOrReplaceTempView("tmp")
    spark.udf.register("getName",(code:String)=>prov_map.get(code).getOrElse(""))
    spark.sql("select getName(_c0) as p from tmp").show()


  }
  val prov_map = Map[String, String]("100" -> "北京", "200" -> "广东", "210" -> "上海", "220" -> "天津",
    "230" -> "重庆", "240" -> "辽宁", "250" -> "江苏", "270" -> "湖北", "280" -> "四川", "290" -> "陕西", "311" -> "河北",
    "351" -> "山西", "371" -> "河南", "431" -> "吉林", "451" -> "黑龙江", "471" -> "内蒙古", "531" -> "山东", "551" -> "安徽",
    "571" -> "浙江", "591" -> "福建", "731" -> "湖南", "771" -> "广西", "791" -> "江西", "851" -> "贵州", "871" -> "云南",
    "891" -> "西藏", "898" -> "海南", "931" -> "甘肃", "951" -> "宁夏", "971" -> "青海", "991" -> "新疆 ")

}
