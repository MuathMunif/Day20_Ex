package seu.demo3.Controller;

import org.springframework.web.bind.annotation.*;
import seu.demo3.Api.ApiReturn;
import seu.demo3.Model.Task;

import java.util.ArrayList;

@RestController
public class TrackerSystemController {

    ArrayList<Task> tasks = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @PostMapping("/add")
    public ApiReturn addTracker(@RequestBody Task task) {
        tasks.add(task);
        return new ApiReturn("success", 200);
    }

    @PutMapping("/update/{id}")
    public ApiReturn updateTracker(@PathVariable int id, @RequestBody Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                task.setId(id);
                tasks.set(i, task);
                return new ApiReturn("Task updated successfully", 200);
            }
        }
        return new ApiReturn("Task not found", 0);
    }


    @DeleteMapping("/delete/{id}")
    public ApiReturn deleteTask(@PathVariable int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.remove(t);
                return new ApiReturn("Task deleted successfully", 200);
            }
        }
        return new ApiReturn("Task not found", 0);
    }


    @PutMapping("/change-status/{id}")
    public ApiReturn changeStatus(@PathVariable int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(!task.isStatus());
                return new ApiReturn("Task status changed", 200);
            }
        }
        return new ApiReturn("Task not found", 0);
    }


    @GetMapping("/search/{title}")
    public ArrayList<Task> searchTask(@PathVariable String title) {
        ArrayList<Task> search = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTitle().equalsIgnoreCase(title)) {
                search.add(t);
            }
        }
        return search;
    }
}
