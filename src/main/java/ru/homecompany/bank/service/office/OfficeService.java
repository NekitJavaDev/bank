package ru.homecompany.bank.service.office;

import org.springframework.validation.annotation.Validated;
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.view.office.OfficeFilter;
import ru.homecompany.bank.view.office.OfficeFilterView;
import ru.homecompany.bank.view.office.OfficeView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service
 */
@Validated
public interface OfficeService {

    /**
     * Get offices by Filter (can be input first chars of a word)
     *
     * @param orgId  Identifier of office's organization (not null)
     * @param filter name, phone, isActive
     * @return @Offices
     */
    List<OfficeFilterView> findByFilter(Integer orgId, @Valid OfficeFilter filter);

    /**
     * Get office by ID
     *
     * @param id Identifier
     * @return @Office
     */
    Office findById(Integer id);

    /**
     * Update office by ID
     *
     * @param view View of office
     */
    void update(@Valid OfficeView view);

    /**
     * Save new office and ID was AUTO INCREMENTED
     *
     * @param view View of office
     */
    void save(@Valid OfficeView view);
}
