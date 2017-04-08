package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by silya on 14.08.2016.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            String name = "";
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && connectionMap.get(name) == null) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            for (String clientName : connectionMap.keySet()) {
                if (!clientName.equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, clientName));
            }
        }

        private  void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (!Thread.currentThread().isInterrupted())
            {
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT)
                {
                    String messageText = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, messageText));
                }
                else
                {
                    ConsoleHelper.writeMessage("произошла ошибка");
                }
            }
        }

        @Override
        public void run()
        {
            ConsoleHelper.writeMessage("Установлено соединение с удаленным клиентом с адресом: " + socket.getRemoteSocketAddress());
            String clientName = null;
            try(Connection connection = new Connection(socket))
            {
                clientName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                sendListOfUsers(connection, clientName);
                serverMainLoop(connection, clientName);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            }
            catch (ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            }
            finally
            {
                if (clientName != null)
                {
                    connectionMap.remove(clientName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
                }
            }
        }
    }
    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            ConsoleHelper.writeMessage("Сервер запущен на порту: " + serverSocket.getLocalPort());
            while (true)
            {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void sendBroadcastMessage(Message message) {

        for (String clientName : connectionMap.keySet()) {
            try {
                connectionMap.get(clientName).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Невозможно отправить сообщение клиенту с именем: " + clientName);
            }
        }
    }

}
