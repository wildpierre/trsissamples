/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.School;

public interface SchoolService {

    Iterable<School> listAll();

    void delete(Integer id);
    
    School add(Integer number, String name);
    
    School findByNumber(Integer number);
}
