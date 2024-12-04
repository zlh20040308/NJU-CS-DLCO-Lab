package lab2

import chisel3._
import chisel3.util._

class PriorityEncoder8_3IO extends Bundle {
  val in  = Input(UInt(8.W))
  val out = Output(UInt(3.W))
}

class PriorityEncoder8_3 extends Module {
  val io = IO(new PriorityEncoder8_3IO)

  val outWire = Wire(UInt(3.W))
  outWire := 0.U

  for (i <- 0 to 7) {
    when(io.in(i)) {
      outWire := i.U
    }
  }

  io.out := outWire
}
