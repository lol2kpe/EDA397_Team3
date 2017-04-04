import com.lol2kpe.h4u.Hospital;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by davidfogelberg on 2017-04-04.
 */

public class HospitalsModel {

    private List<Hospital> hospitals = new LinkedList();

    public List<Hospital> getHospitals() {
        return hospitals;
    }
    public void addHospitals(Hospital hospital) {
        hospitals.add(hospital);
    }
}
