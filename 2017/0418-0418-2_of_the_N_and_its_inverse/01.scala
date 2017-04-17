import java.nio.file.{Files, Paths}
import scala.collection.JavaConverters._

val tuples = (0 to 20) // 生成所有指数序列
  .map { // 根据指数序列生成2的N次方值
    case 0 => 1
    case 1 => 2
    case idx => (0 until idx).foldLeft(2)((s, _) => s * 2)
  }
  .map(n => (n.toString, (BigDecimal(1) / n).bigDecimal.toPlainString)) // 2的N次方及其倒数

val (maxNum, maxRec) = tuples.last // 解构最后一组tuple

val nLen = maxNum.length // 最大数值的字符串表示长度
val rLen = maxRec.length // 最大倒数的字符串表示长度

val results = tuples
  .map { case (num, rec) => // 格式化字符串
    val numStr = (0 until nLen - num.length).map(_ => ' ').mkString + num
    val recStr = (0 until rLen - rec.length).map(_ => ' ').mkString + rec
    numStr + "    " + recStr
  }

Files.write(Paths.get("results.txt"), results.asJava) // 将结果写入文件
