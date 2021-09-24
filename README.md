Пример перенесенного скрипта из динамического кода в пользовательский плагин.  

Для начала использования папку custom поместить в корневую папку BGERP и в интерфейсе (+ -> Администрирование - Custom) нажать "Скомпилировать всё" и перезапустить приложение.  
В активную конфигурацию добавиь параметры  

#Параметр типа date в который нужно отправить напоминание  
custom.global:paramId=1  
#chat id группы или пользователя telegram  
custom.global:chatId=1234567890  

Добавить задачу планировщика:  
scheduler.task.1.class=org.bgerp.plugin.custom.global.TelegramGlobalNotifications  
scheduler.task.1.minutes=0  
scheduler.task.1.hours=10  
