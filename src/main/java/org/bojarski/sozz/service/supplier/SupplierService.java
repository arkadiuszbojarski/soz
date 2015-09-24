package org.bojarski.sozz.service.supplier;

import org.bojarski.sozz.model.domain.supplier.Supplier;
import org.bojarski.sozz.model.domain.supplier.SupplierSearchConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Interfejs serwisu dostawców.
 * @author Arkadiusz Bojarski
 *
 */
public interface SupplierService {

    /**
     * Metoda pozwalająca na utworzenie dostawców.
     * @param supplier tworzony dostawca.
     * @return utworzony dostawca.
     */
    public Supplier create(Supplier supplier);
   
    /**
     * Metoda pozwalająca na odczytanie dostawcy o podanym id.
     * @param id odczytywanego dostawcy.
     * @return odczytany dostawca.
     */
    public Supplier read(Long id);
    
    /**
     * Metoda pozwalająca na zmodyfikowanie dostawcy o podanym id.
     * @param id modyfikowanego dostawcy.
     * @param supplier dostawca stanowiący postać jaką ma przyjąć modyfikowany dostawca.
     * @return zmodyfikowany dostawca.
     */
    public Supplier update(Long id, Supplier supplier);
    
    /**
     * Metoda pozwalająca na wyszukanie dostawców z wykorzystaniem
     * frazy oraz kryteriów wyszukiwania.
     * @param query napis będący frazą wyszukiwania.
     * @param conditions kryteria wyszukiwania dostawców.
     * @param pageRequest informacje określające stronę.
     * @return strona dostawców wyszukanych z wykorzystaniem frazy i kryteriów wyszukiwania.
     */
    public Page<Supplier> search(String query, SupplierSearchConditions conditions, PageRequest pageRequest);
    
    /**
     * Metoda pozwalająca na usunięcie dostawcy o podanym id.
     * @param id usuwanego dostawcy.
     */
    public void delete(Long id);
    
}