#include "common.h"

void lab2(TOP_NAME *top) {
  while (1) {
    top->eval();
    nvboard_update();
  }
}
