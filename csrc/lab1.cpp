#include "common.h"

void lab1(TOP_NAME *top) {
  while (1) {
    top->eval();
    nvboard_update();
  }
}
