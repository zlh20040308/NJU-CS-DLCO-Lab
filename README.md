# NJU-CS-DLCO-Lab  

This repository is dedicated to learning and experimenting with digital logic design. **Please adhere to academic integrity guidelines and refrain from any form of academic misconduct.**  

## Prerequisites  

To simulate the provided code, ensure the following dependencies are installed and properly configured:  
  
- **Verilator** version **5.008**  
- **nvboard** project  
  
Set the `NVBOARD_HOME` environment variable to the local path of your `nvboard` repository. For example:  
```bash  
export NVBOARD_HOME=/path/to/your/nvboard  
```  
  
## Usage  

The experimental projects are implemented using **Chisel**, a hardware construction language. Each lab is designed to build on foundational digital logic concepts and includes both Verilog generation and simulation processes.  
  
To run simulations for a specific lab, follow these general steps:  

1. **Update the** `Makefile`:  
Set the `PROJECT_NAME` variable to match the lab you want to simulate. For example, for `lab3`:  
  
```makefile  
export PROJECT_NAME = lab3  
```  
  
2. **Update the** `sim-main.cpp` **File**:  
Modify the `LABX` macro definition to match the target lab. For example, for `lab3`:  
```cpp  
#define LABX 3  
```  

3. **Run the Simulation**:  
Execute the following commands sequentially:

- Generate Verilog files:  
```bash  
make verilog  
```  

- Run the simulation:  
```bash  
make run  
```  

## Notes  
- Ensure that all required environment variables are correctly configured before executing the simulation commands.  
- The repository and its content are intended solely for educational purposes. Modifications and reuse must comply with the principles of academic honesty.  
