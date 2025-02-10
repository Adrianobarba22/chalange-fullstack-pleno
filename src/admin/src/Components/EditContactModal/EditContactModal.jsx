import React, { useState } from 'react';
import './EditContactModal.css';

const EditContactModal = ({ contact, onClose, onSave }) => {
  const [editedContact, setEditedContact] = useState({
    firstName: contact.firstName,
    lastName: contact.lastName,
    email: contact.email,
    phoneNumbers: contact.phoneNumbers.map((phone) => phone.number),
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedContact({ ...editedContact, [name]: value });
  };

  const handlePhoneNumberChange = (index, value) => {
    const updatedPhones = [...editedContact.phoneNumbers];
    updatedPhones[index] = value;
    setEditedContact({ ...editedContact, phoneNumbers: updatedPhones });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(editedContact);
  };

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>Editar Contato</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Nome:</label>
            <input
              type="text"
              name="firstName"
              value={editedContact.firstName}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Sobrenome:</label>
            <input
              type="text"
              name="lastName"
              value={editedContact.lastName}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="form-group">
            <label>E-mail:</label>
            <input
              type="email"
              name="email"
              value={editedContact.email}
              onChange={handleInputChange}
              required
            />
          </div>
          <div className="phone-numbers">
            <label>Números de Telefone:</label>
            {editedContact.phoneNumbers.map((phone, index) => (
              <div key={index} className="phone-number-field">
                <input
                  type="text"
                  value={phone}
                  onChange={(e) => handlePhoneNumberChange(index, e.target.value)}
                  required={index === 0}
                />
              </div>
            ))}
          </div>
          <div className="modal-buttons">
            <button type="button" onClick={onClose}>
              Cancelar
            </button>
            <button type="submit">Salvar</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditContactModal;