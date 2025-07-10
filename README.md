# Computer Store System
A comprehensive Java-based computer hardware management application with an interactive PC builder.

---

## Overview
Computer Store System is a JavaFX desktop application that allows users to manage computer component inventory and build custom PC configurations. The application features a modern GUI with inventory management, PC building wizard, and compatibility checking system.

---

## Features
- **Inventory Management**: Browse and manage 8 types of PC components (CPU, GPU, RAM, Motherboard, Storage, PSU, Case, Cooling)
- **Interactive PC Builder**: Step-by-step wizard to build custom PC configurations
- **Compatibility Checking**: Automatic validation of component compatibility (socket types, power requirements, etc.)
- **Data Persistence**: CSV-based storage for all component specifications
- **Modern GUI**: Clean JavaFX interface with custom styling and animations
- **Component Search**: Filter and search through extensive component databases

---

## Supported Components
| Component Type | Key Specifications | Sample Data |
|----------------|-------------------|-------------|
| **CPU** | Socket, TDP, Chipset, Memory Support | 21 processors (Intel & AMD) |
| **GPU** | TDP, Memory, Performance tier | Various graphics cards |
| **RAM** | Type (DDR4/DDR5), Capacity, Speed | Multiple memory configurations |
| **Motherboard** | Socket, Chipset, Form Factor, Memory Slots | Compatible with CPU sockets |
| **Storage** | Type (SSD/HDD), Capacity, Interface | SATA and NVMe options |
| **PSU** | Wattage, Efficiency Rating, Modularity | Power supplies for all builds |
| **Case** | Form Factor, GPU clearance, Drive bays | ATX, mATX, ITX cases |
| **Cooling** | Type (Air/AIO), Socket compatibility, TDP rating | CPU coolers and case fans |

---

## Project Structure
```
src/main/java/com/mahmoud/computerstore/
├── ComputerStoreApp.java          # Main JavaFX application entry point
├── SimpleTest.java                # Data loading test utility
├── controller/                    # FXML controllers for UI management
│   ├── MainController.java        # Home screen navigation
│   ├── InventoryController.java   # Inventory navigation hub
│   ├── PCBuilderController.java   # PC building wizard logic
│   ├── BuildController.java       # Build state management
│   ├── CPUController.java         # CPU inventory management
│   ├── GPUController.java         # GPU inventory management
│   ├── RAMController.java         # RAM inventory management
│   ├── MotherboardController.java # Motherboard inventory management
│   ├── StorageController.java     # Storage inventory management
│   ├── PSUController.java         # PSU inventory management
│   ├── CaseController.java        # Case inventory management
│   └── CoolingController.java     # Cooling inventory management
├── model/                         # Data models for components
│   ├── Build.java                 # Complete PC build representation
│   ├── CPU.java                   # CPU component model
│   ├── GPU.java                   # GPU component model
│   ├── RAM.java                   # RAM component model
│   ├── Motherboard.java           # Motherboard component model
│   ├── Storage.java               # Storage component model
│   ├── PSU.java                   # PSU component model
│   ├── Case.java                  # Case component model
│   └── Cooling.java               # Cooling component model
├── service/                       # Business logic and data services
│   ├── DataService.java           # Component data loading/saving
│   └── CompatibilityChecker.java  # Component compatibility validation
└── util/                          # Utility classes
    └── CSVLoader.java              # CSV file parsing utility

src/main/resources/
├── com/mahmoud/computerstore/
│   ├── view/                      # FXML view files
│   │   ├── main_view.fxml         # Home screen layout
│   │   ├── inventory_view.fxml    # Inventory navigation screen
│   │   ├── pc_builder_view.fxml   # PC builder wizard interface
│   │   ├── cpu_view.fxml          # CPU management interface
│   │   ├── gpu_view.fxml          # GPU management interface
│   │   ├── ram_view.fxml          # RAM management interface
│   │   ├── motherboard_view.fxml  # Motherboard management interface
│   │   ├── storage_view.fxml      # Storage management interface
│   │   ├── psu_view.fxml          # PSU management interface
│   │   ├── case_view.fxml         # Case management interface
│   │   └── cooling_view.fxml      # Cooling management interface
│   └── images/                    # UI assets and backgrounds
└── data/                          # CSV data files
    ├── cpu.csv                    # CPU specifications database
    ├── gpu.csv                    # GPU specifications database
    ├── ram.csv                    # RAM specifications database
    ├── motherboard.csv            # Motherboard specifications database
    ├── storage.csv                # Storage specifications database
    ├── psu.csv                    # PSU specifications database
    ├── case.csv                   # Case specifications database
    └── cooling.csv                # Cooling specifications database

build.gradle                      # Gradle build configuration
gradlew / gradlew.bat             # Gradle wrapper scripts
```

---

## Requirements
- **Java**: Version 21 or higher
- **JavaFX**: Version 21 (handled automatically by Gradle plugin)
- **Gradle**: Version 8.13 or higher (included via wrapper)
- **Operating System**: Windows, macOS, or Linux

---

## Installation & Setup

### 1. Clone or Download
```bash
git clone <your-repository-url>
cd ComputerStoreSystem
```

### 2. Run the Application
**Option A: Using Gradle (Recommended)**
```bash
# Windows
./gradlew run

# macOS/Linux
./gradlew run
```

**Option B: Using Your IDE**
- Open the project in IntelliJ IDEA, Eclipse, or VS Code
- Run the `ComputerStoreApp.java` main class
- Ensure JavaFX modules are properly configured

### 3. Test Data Loading (Optional)
```bash
# Test component data loading
./gradlew run --args="com.mahmoud.computerstore.SimpleTest"
```

---

## Usage Guide

### Navigation
1. **Home Screen**: Launch point with options for Inventory and PC Builder
2. **Inventory**: Browse and manage individual component categories
3. **PC Builder**: Step-by-step wizard to create custom builds

### PC Building Process
1. **CPU Selection**: Choose processor (determines socket compatibility)
2. **Motherboard**: Select compatible motherboard (matching CPU socket)
3. **Cooling**: Pick CPU cooler (socket and TDP compatible)
4. **RAM**: Choose memory (matching motherboard memory type)
5. **Storage**: Select storage devices (SSD/HDD options)
6. **GPU**: Pick graphics card (PSU power consideration)
7. **Case**: Choose case (form factor and clearance)
8. **PSU**: Select power supply (wattage for all components)

### Compatibility Features
- **Socket Matching**: CPU and motherboard socket validation
- **Memory Type**: DDR4/DDR5 compatibility checking
- **Power Requirements**: PSU wattage vs component TDP calculation
- **Physical Fit**: GPU clearance and case form factor validation

---

## Data Management
The application uses CSV files for component data storage:
- Component specifications are loaded from `/src/main/resources/data/`
- Each component type has its dedicated CSV file
- Data includes compatibility specifications for validation
- Sample data includes real-world components for testing

---

## Troubleshooting

### Common Issues
**JavaFX Module Errors**: Ensure you're using Java 21+ and the JavaFX plugin is properly configured
**CSV Loading Failures**: Verify CSV files are in the correct `/resources/data/` directory
**Build Compatibility Issues**: Check that component specifications match real-world compatibility rules

### Debug Mode
Use the `SimpleTest.java` class to verify data loading:
```bash
# Loads and displays all components from CSV files
./gradlew run --args="com.mahmoud.computerstore.SimpleTest"
```

---

## Development Notes
- **Architecture**: MVC pattern with JavaFX controllers
- **Data Model**: Immutable component classes with comprehensive specifications
- **Compatibility Logic**: Centralized in `CompatibilityChecker` service
- **UI Framework**: JavaFX with FXML for declarative UI design
- **Build Tool**: Gradle with JavaFX plugin for dependency management

---

## Future Enhancements
- Database integration (replace CSV files)
- Component pricing and budget tracking
- Performance benchmarking integration
- Component availability and vendor information
- Export build configurations to PDF/text formats
- Advanced filtering and search capabilities

---

## Contributing
1. Fork the repository
2. Create a feature branch
3. Add comprehensive tests for new components/features
4. Follow the existing code structure and naming conventions
5. Submit a pull request with detailed descriptions

---

## License
This project is available for educational and personal use. See license file for details.
