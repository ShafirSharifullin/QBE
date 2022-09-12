package pro.siberian.dynamicsql

import jakarta.validation.constraints.Pattern
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import pro.siberian.qbe.companies.Company
import pro.siberian.qbe.companies.CompanyService
import pro.siberian.qbe.employees.Employee
import pro.siberian.qbe.employees.EmployeeService
import java.time.LocalDateTime


@RestController
@RequestMapping("/qbe")
@Validated
class Controller(
    private val companyServ: CompanyService,
    private val employeeServ: EmployeeService,
) {

    @GetMapping("/companies")
    fun getCompanies(
        @RequestParam("name") name: String?,
        @RequestParam("sort", defaultValue = "id_old")
        @Pattern(regexp = "date_create_new|date_create_old|id_new|id_old") sort: String,
    ): List<Company> {
        return companyServ.findAll(name, sort).toList()
    }

    @GetMapping("/employees")
    fun getEmployees(): List<Employee> {
        return employeeServ.findAll()
    }

    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCompany(
        @RequestParam("name") name: String,
    ) {
        companyServ.save(Company(0, name, LocalDateTime.now()))
    }

    @PostMapping("/employee")
    fun createEmployee(
        @RequestParam("name") name: String,
        @RequestParam("status") @Pattern(regexp = "full_time_emp|freelance_emp") status: String,
        @RequestParam("salary") salary: Int,
        @RequestParam("year_birth") yearBirth: Int,
        @RequestParam("company_id") companyId: Long,
    ): ResponseEntity<Any> {
        if (companyServ.findById(companyId) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("company not found")

        employeeServ.save(Employee(0, name, status, salary, yearBirth, companyId))

        return ResponseEntity.status(HttpStatus.CREATED).body("")
    }
}