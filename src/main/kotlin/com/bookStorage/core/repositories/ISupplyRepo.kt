package com.bookStorage.core.repositories

import com.bookStorage.core.models.SupplyModel
import java.util.UUID

interface ISupplyRepo {
    fun CreateSupply(supplyModel: SupplyModel): Boolean
    fun UpdateSupply(supplyModel: SupplyModel): Boolean
    fun DeleteSupplyById(id: UUID): Boolean
    fun GetAllSuplliesByPage(page: Int, pageSize: Int): SupplyModel
    fun GetSuplyById(id: UUID): SupplyModel

    /**
     * update only field named realDeliveryDate by current date
     * @param id: UUID - id of supplyModel
     */
    fun SetDateOfRealDeliveryDate(id: UUID): Boolean // можно в целом было бы реализовать и просто через отправку update
                                                    // с новой датой, но мне так кажется тупо правильней, чтоб при
                                                    // при обновлении даты когда товар быд привезен, не обновили, например и имя

    // TODO проверки на то можно ли привезти столько книг за раз или нет, будут в сервисе. Если нельзя, то тупо не одобряем создаваемую поставку


}