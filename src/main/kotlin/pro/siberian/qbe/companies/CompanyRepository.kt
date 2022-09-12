package pro.siberian.qbe.companies

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.QueryByExampleExecutor
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : CrudRepository<Company, Long>, QueryByExampleExecutor<Company>