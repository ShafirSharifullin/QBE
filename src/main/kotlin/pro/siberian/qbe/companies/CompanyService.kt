package pro.siberian.qbe.companies

import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepo: CompanyRepository) {

    fun findAll(name: String?, sort: String): Set<Company> {
        val matcher = ExampleMatcher.matching()
            .withIgnorePaths("id")
            .withIgnorePaths("dateCreate")
            .withIgnorePaths("employees")
            .withMatcher("name", ignoreCase().contains())
            .withStringMatcher(ExampleMatcher.StringMatcher.ENDING)

        val company = Company(name = name ?: "%")

        val example: Example<Company> = Example.of(company, matcher)

        val sortBy = when (sort) {
            "date_create_new" -> Sort.by(Sort.Direction.DESC, "date_create")
            "date_create_old" -> Sort.by(Sort.Direction.ASC, "date_create")
            "id_new" -> Sort.by(Sort.Direction.DESC, "id")
            else -> Sort.by(Sort.Direction.ASC, "id")
        }

        return companyRepo.findAll(example, sortBy).toSet()
    }

    fun findAll() = companyRepo.findAll().toList()

    fun findById(id: Long): Company? = companyRepo.findById(id).orElse(null)

    fun save(company: Company) = companyRepo.save(company)
}