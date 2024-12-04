# Project and directory setup
export PROJECT_NAME = lab2
export SRC_DIR = $(PROJECT_NAME)/src
export TARGET_DIR = ./vsrc/$(PROJECT_NAME)

TOP_NAME := Top
NXDC_FILES = constr/$(PROJECT_NAME)_top.nxdc
VERILATOR = verilator

# Source files
CSRC += $(shell find $(abspath ./csrc/) -name "*.cpp")
VSRC += $(shell find $(abspath ./vsrc/$(PROJECT_NAME)) -name "*.sv")
HSRC += $(shell find $(abspath ./csrc/) -name "*.h")

BUILD_DIR = ./$(PROJECT_NAME)/build
OBJ_DIR = $(BUILD_DIR)/obj_dir

# Create build directory
$(shell mkdir -p $(BUILD_DIR))
$(shell mkdir -p $(TARGET_DIR))

# Auto-bind constraint file
SRC_AUTO_BIND = $(abspath $(BUILD_DIR)/auto_bind.cpp)
$(SRC_AUTO_BIND): $(NXDC_FILES)
	python3 $(NVBOARD_HOME)/scripts/auto_pin_bind.py $^ $@

# Rules for NVBoard
include $(NVBOARD_HOME)/scripts/nvboard.mk

# Verilator flags
VERILATOR_FLAGS += -I$(VSRC)
VERILATOR_FLAGS += --trace --debug --cc --exe --build
VERILATOR_FLAGS += -Wno-lint
VERILATOR_FLAGS += -Wno-style
VERILATOR_FLAGS += -Wno-UNUSED

# Compiler and linker flags
INC_PATH		?= csrc/
CXXFLAGS	    += -g
CXXFLAGS		+= -I$(INC_PATH)
CXXFLAGS        += -DTOP_NAME="\"V$(TOP_NAME)\""

BIN				:= $(OBJ_DIR)/V$(TOP_NAME)
ARGS ?= 
LAB_EXEC		:= ${BIN}

.PHONY: run gdb wave clean com verilog format

# Compilation rule
com: ${VSRC} ${CSRC} $(SRC_AUTO_BIND) $(NVBOARD_ARCHIVE)
	rm -rf $(OBJ_DIR)
	rm -rf ./*.vcd
	$(VERILATOR) $(VERILATOR_FLAGS) \
		--top-module $(TOP_NAME) $^ \
		$(addprefix -CFLAGS , $(CXXFLAGS)) $(addprefix -LDFLAGS , $(LDFLAGS)) \
		--Mdir $(OBJ_DIR) --exe -o $(abspath $(BIN))

# Debug the simulation
gdb:com
	@gdb --silent -s ${BIN} --args ${LAB_EXEC}


# Run the simulation
run: com
	@${LAB_EXEC}

# Generate Verilog output
verilog:
	mkdir -p $(TARGET_DIR)
	rm -rf $(TARGET_DIR)/*
	mill -i Lab.runMain $(PROJECT_NAME).Elaborate --target-dir $(TARGET_DIR)

clean:
	rm -rf compile_commands.json
	rm -rf ./lab*/build
	rm -rf ./vsrc/lab*
	rm -rf ./out

format:
	mill Lab.reformat	