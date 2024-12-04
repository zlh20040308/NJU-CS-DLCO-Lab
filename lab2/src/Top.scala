package lab2

import chisel3._
import chisel3.util._

class Top extends Module {
  val io = IO(new Bundle {
    val btn      = Input(UInt(8.W))
    val out      = Output(UInt(7.W))
    val hasInput = Output(Bool())
  })

  val msb = Module(new PriorityEncoder8_3())

  io.hasInput := io.btn.orR
  msb.io.in   := io.btn
//    a
//  -----
// f| g |b
//  -----
// e|   |c
//  -----
//    d
  io.out := MuxCase(
    0.U,
    Seq(
      (msb.io.out === 0.U) -> "b0000001".U(7.W),
      (msb.io.out === 1.U) -> "b1001111".U(7.W),
      (msb.io.out === 2.U) -> "b0010010".U(7.W),
      (msb.io.out === 3.U) -> "b0000110".U(7.W),
      (msb.io.out === 4.U) -> "b1001100".U(7.W),
      (msb.io.out === 5.U) -> "b0100100".U(7.W),
      (msb.io.out === 6.U) -> "b0100000".U(7.W),
      (msb.io.out === 7.U) -> "b0001111".U(7.W),
      (msb.io.out === 8.U) -> "b0000000".U(7.W),
      (msb.io.out === 9.U) -> "b0000100".U(7.W)
    )
  )

}
