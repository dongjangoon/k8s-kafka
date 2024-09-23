import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";
import {Client} from "@stomp/stompjs";

const API_BASE_URL = 'http://localhost:8080/api';

function App() {
  const [notifications, setNotifications] = useState([]);
  const [newNotification, setNewNotification] = useState('');
  const [latestWord, setLatestWord] = useState('');

  useEffect(() => {
    fetchNotifications();

    const client = new Client({
      brokerURL: 'ws://localhost:8080/api/v1/notifications/ws',
      onConnect: () => {
        console.log('Connected to WebSocket');
        client.subscribe('/topic/words', (message) => {
          const notification = JSON.parse(message.body);
          setLatestWord(notification.content);
          setNotifications(prev => [notification, ...prev]);
        })
      },
      onStompError: ((frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
      })
    })

    client.activate();

    return () => {
      client.deactivate();
    };
  }, []);

  const fetchNotifications = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/v1/notifications`);
      setNotifications(response.data);
    } catch (error) {
      console.error('Error fetching notifications', error);
    }
  }

  const createNotification = async () => {
    try {
      await axios.post(`${API_BASE_URL}/v1/notifications`, newNotification);
      setNewNotification('');
      fetchNotifications();
    } catch (error) {
      console.error('Error creating notification', error);
    }
  }

  const deleteNotification = async (id) => {
    try {
      await axios.delete(`${API_BASE_URL}/v1/notifications/${id}`);
      fetchNotifications();
    } catch (error) {
      console.error('Error deleting notification', error);
    }
  }

  return (
    <div className="App">
      <h1>Word Combination Generator</h1>
      <h2>Latest Word Combination: {latestWord}</h2>
      <h3>Notifications</h3>
      <div>
        <input
          type="text"
          value={newNotification}
          onChange={(e) => setNewNotification(e.target.value)}
          placeholder="Enter a word combination"
        />
        <button onClick={createNotification}>Create Notification</button>
      </div>
      <ul>
        {notifications.map((notification) => (
          <li key={notification.id}>
            {notification.content}
            <button onClick={() => deleteNotification(notification.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
