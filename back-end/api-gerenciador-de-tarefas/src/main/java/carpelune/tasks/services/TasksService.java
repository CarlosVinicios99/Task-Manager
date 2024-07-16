package carpelune.tasks.services;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.tasks.dto.CreateTaskDTO;
import carpelune.tasks.dto.DeleteTaskDTO;
import carpelune.tasks.dto.UpdateTaskDTO;
import carpelune.tasks.models.Task;
import carpelune.tasks.repositories.TasksRepository;
import carpelune.workspaces.models.Workspace;

@Service
public class TasksService {
	
	private Logger logger = Logger.getLogger(TasksService.class.getName());
	
	@Autowired
	private TasksRepository tasksRepository;
	
	
	public ResponseEntity<Task> createTask(CreateTaskDTO createTaskDTO){
		
		this.logger.log(Level.INFO, "creating a new task");
		
		try {
			Task newTask = new Task();
			
			newTask.setCreationTimestamp(Instant.now().toEpochMilli());
			newTask.setCreatorId(createTaskDTO.creatorId());
			newTask.setStatus(createTaskDTO.status());
			newTask.setDescription(createTaskDTO.description());
			newTask.setExpectedConclusionTimestamp(createTaskDTO.expectedConclusionTimestamp());
			newTask.setPriority(createTaskDTO.priority());
			newTask.setProjectId(createTaskDTO.projectId());
			newTask.setTitle(createTaskDTO.title());
			newTask.setWorkerId(createTaskDTO.workerId());
			
			this.logger.log(Level.WARNING, "saving new task in the database");
			newTask = this.tasksRepository.save(newTask);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while creating a task");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}
	
	public ResponseEntity<Task> findTaskById(UUID taskId){
		
		this.logger.log(Level.INFO, "fetching task by ID");
		
		try {
			this.logger.log(Level.WARNING, "fetching task from the database");
			Task searchedTask = this.tasksRepository.findById(taskId).get();
			
			if(searchedTask == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing task");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(searchedTask);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while fetching task by ID " + error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Void> updateTask(UpdateTaskDTO updateTaskDTO){
		
		this.logger.log(Level.INFO, "updating task by ID");
		
		if(
			updateTaskDTO.creatorId() == null || updateTaskDTO.creatorId().toString().isEmpty()
			&& updateTaskDTO.workerId() == null || updateTaskDTO.workerId().toString().isEmpty()
		) {
			this.logger.log(Level.WARNING, "no user ID involved in the task provided");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			
			this.logger.log(Level.WARNING, "fetching task from the database");
			Task updatedTask = this.tasksRepository.findById(updateTaskDTO.id()).get();
			
			if(
				updatedTask.getWorkerId() != updateTaskDTO.workerId() &&
				updatedTask.getCreatorId() != updateTaskDTO.creatorId() 
			){
				this.logger.log(Level.WARNING, "none of the provided user IDs are associated with the task");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
			Field[] fields = UpdateTaskDTO.class.getDeclaredFields();
			
			for(Field field : fields) {
	            field.setAccessible(true);
	            Object value = field.get(updateTaskDTO);

	            if (value != null) {
	                try {
	                    Field TaskField = Workspace.class.getDeclaredField(field.getName());
	                    TaskField.setAccessible(true);
	                    TaskField.set(updatedTask, value);
	                } 
	                catch(NoSuchFieldException e) {
	                    logger.log(Level.WARNING, "Field " + field.getName() + "field not found in task");
	                }
	            }
	        }
			
			this.logger.log(Level.WARNING, "updating task in the database");
			this.tasksRepository.save(updatedTask);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while updating task " + error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Void> deleteTask(DeleteTaskDTO deleteTaskDTO){
		
		this.logger.log(Level.INFO, "deleting a task");
		
		try {
			this.logger.log(Level.WARNING, "fetching task from the database");
			Task deletedTask = this.tasksRepository.findById(deleteTaskDTO.id()).get();
			
			if(deletedTask == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing task");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			if(deletedTask.getCreatorId() != deleteTaskDTO.creatorId()) {
				this.logger.log(Level.WARNING, "the provided creator ID is not associated with the task");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
			this.tasksRepository.deleteById(deleteTaskDTO.id());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while deleting task");
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
}
