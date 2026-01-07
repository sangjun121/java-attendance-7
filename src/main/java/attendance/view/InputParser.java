package attendance.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {
    public Map<String, List<LocalDateTime>> parseAttendances(List<String> input) {
        Map<String, List<LocalDateTime>> attendances = new HashMap<>();

        input.removeFirst();
        for (String attendance : input) {
            String[] nameAndDateTime = attendance.split(",");
            String nickName = nameAndDateTime[0];
            LocalDateTime time = parseDateTime(nameAndDateTime[1]);

            if (attendances.containsKey(nickName)) {
                attendances.get(nickName).add(time);
            } else {
                attendances.put(nickName, new ArrayList<>(List.of(time)));
            }
        }

        return attendances;
    }

    private LocalDateTime parseDateTime(String input) {
        String formattedInput = input.replace(" ", "T").concat(":00");
        return LocalDateTime.parse(formattedInput);
    }
}
