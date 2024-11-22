#include "common.h"

#define LABX 1

static TOP_NAME *dut;
void nvboard_bind_all_pins(TOP_NAME *top);
void lab1(TOP_NAME *top);

int main() {
  dut = new TOP_NAME;
  nvboard_bind_all_pins(dut);
  nvboard_init();
  switch (LABX) {
  case 1:
    lab1(dut);
    break;
  default:
    break;
  }
  delete dut;
  nvboard_quit();
}