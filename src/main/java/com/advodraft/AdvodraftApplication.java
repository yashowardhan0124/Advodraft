package com.advodraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ═══════════════════════════════════════════════════════════════════
 * STEP 2: Same as Step 1, now with DATABASE POWER! 🗄️
 * ═══════════════════════════════════════════════════════════════════
 * 
 * NEW IN STEP 2:
 * - Connects to PostgreSQL database
 * - Creates tables automatically (via JPA)
 * - Can save and retrieve data
 * 
 * @SpringBootApplication still does the same thing:
 * - Starts Spring Boot
 * - Scans for components
 * - Auto-configures (now includes database!)
 * 
 * ═══════════════════════════════════════════════════════════════════
 */
@SpringBootApplication
public class AdvodraftApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvodraftApplication.class, args);
        
        System.out.println("\n✅ Advodraft is running with DATABASE on http://localhost:8080\n");
    }
}
