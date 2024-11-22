package lab1

import chisel3._
import chisel3.util._

class Top extends Module {
  val io = IO(new Bundle {
    val X0 = Input(UInt(2.W))
    val X1 = Input(UInt(2.W))
    val X2 = Input(UInt(2.W))
    val X3 = Input(UInt(2.W))
    val Y  = Input(UInt(2.W))
    val F  = Output(UInt(2.W))
  })

  io.F := MuxCase(
    io.X0,
    Array((io.Y === 0.U) -> io.X0, (io.Y === 1.U) -> io.X1, (io.Y === 2.U) -> io.X2, (io.Y === 3.U) -> io.X3)
  )

}
