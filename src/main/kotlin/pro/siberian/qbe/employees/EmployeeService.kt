package pro.siberian.qbe.employees

import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepo: EmployeeRepository) {

    fun findAll() = employeeRepo.findAll().toList()

    fun save(employee: Employee) = employeeRepo.save(employee)
}