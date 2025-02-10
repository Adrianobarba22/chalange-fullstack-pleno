import React, { useState, useEffect } from 'react';
import './ListContact.css';
import cross_icon from '../../assets/cross_icon.png';
import EditContactModal from '../EditContactModal/EditContactModal';

const ListContact = () => {
  const [contacts, setContacts] = useState([]);
  const [editingContact, setEditingContact] = useState(null);
  const [sortOrder, setSortOrder] = useState('asc'); // 'asc' ou 'desc'

  const fetchContacts = async () => {
    try {
      const response = await fetch('http://localhost:8080/contacts');
      const data = await response.json();
      setContacts(data);
    } catch (error) {
      console.error('Error fetching contacts:', error);
    }
  };

  useEffect(() => {
    fetchContacts();
  }, []);

  const deleteContact = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/contacts/${id}`, {
        method: 'DELETE',
      });
      if (response.ok) {
        setContacts(contacts.filter((contact) => contact.id !== id));
      } else {
        console.error('Failed to delete contact');
      }
    } catch (error) {
      console.error('Error deleting contact:', error);
    }
  };

  const updateContact = async (id, updatedContact) => {
    try {
      const response = await fetch(`http://localhost:8080/contacts/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedContact),
      });
      if (response.ok) {
        fetchContacts(); // Recarrega a lista de contatos
      } else {
        console.error('Failed to update contact');
      }
    } catch (error) {
      console.error('Error updating contact:', error);
    }
  };

  const handleEditClick = (contact) => {
    setEditingContact(contact);
  };

  const handleSave = (updatedContact) => {
    updateContact(editingContact.id, updatedContact);
    setEditingContact(null);
  };

  const handleSort = () => {
    const sortedContacts = [...contacts].sort((a, b) => {
      if (sortOrder === 'asc') {
        return a.firstName.localeCompare(b.firstName);
      } else {
        return b.firstName.localeCompare(a.firstName);
      }
    });
    setContacts(sortedContacts);
    setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
  };

  return (
    <div className="list-contact">
      <h1>Contatos</h1>
      
      <div className="listcontact-header">
        <p>Nome</p>
        <p>Sobrenome</p>
        <p>Email</p>
        <p>Telefones</p>
        <p><button onClick={handleSort}>
         ({sortOrder === 'asc' ? 'A-Z' : 'Z-A'})
      </button></p>
      </div>
      <div className="listcontact-contacts">
        <hr />
        {contacts.map((contact) => (
          <div key={contact.id} className="listcontact-item">
            <p>{contact.firstName}</p>
            <p>{contact.lastName}</p>
            <p>{contact.email}</p>
            <div>
              {contact.phoneNumbers.map((phone) => (
                <p key={phone.id}>{phone.number}</p>
              ))}
            </div>
            <div className="actions">
              <button onClick={() => handleEditClick(contact)}>Editar</button>
              <img
                onClick={() => deleteContact(contact.id)}
                className="listcontact-remove-icon"
                src={cross_icon}
                alt="Remove Contact"
              />
            </div>
          </div>
        ))}
      </div>
      {editingContact && (
        <EditContactModal
          contact={editingContact}
          onClose={() => setEditingContact(null)}
          onSave={handleSave}
        />
      )}
    </div>
  );
};

export default ListContact;