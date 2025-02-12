import React from 'react';
import useForm from '../../hooks/useForm';
import './AddContact.css';

const AddContact = () => {
  const [contactDetails, handleInputChange, resetForm] = useForm({
    firstName: '',
    lastName: '',
    email: '',
    phoneNumbers: [''],
  });

  const handlePhoneNumberChange = (index, value) => {
    const updatedPhones = [...contactDetails.phoneNumbers];
    updatedPhones[index] = value;
    handleInputChange({
      target: {
        name: 'phoneNumbers',
        value: updatedPhones,
      },
    });
  };

  const addPhoneField = () => {
    if (contactDetails.phoneNumbers.length < 4) {
      handleInputChange({
        target: {
          name: 'phoneNumbers',
          value: [...contactDetails.phoneNumbers, ''],
        },
      });
    } else {
      alert('Você não pode adicionar mais do que 4 números de telefone.');
    }
  };

  const removePhoneField = (index) => {
    const updatedPhones = contactDetails.phoneNumbers.filter((_, i) => i !== index);
    handleInputChange({
      target: {
        name: 'phoneNumbers',
        value: updatedPhones,
      },
    });
  };

  const handleSubmit = async () => {
    const payload = {
      ...contactDetails,
      phoneNumbers: contactDetails.phoneNumbers
        .filter((phone) => phone.trim() !== '')
        .map((phone) => ({ number: phone })),
    };

    try {
      const response = await fetch('http://localhost:8080/contacts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        alert('Contato cadastrado com sucesso!');
        resetForm();
      } else {
        alert('Erro ao cadastrar o contato. Verifique os dados e tente novamente.');
      }
    } catch (error) {
      console.error('Erro na requisição:', error);
      alert('Ocorreu um erro inesperado. Tente novamente mais tarde.');
    }
  };

  return (
    <div className="add-contact">
      <h2>Cadastrar Novo Contato</h2>
      <div className="addcontact-itemfield">
        <p>Nome:</p>
        <input
          type="text"
          name="firstName"
          value={contactDetails.firstName}
          onChange={handleInputChange}
          placeholder="Digite o nome"
          required
        />
      </div>
      <div className="addcontact-itemfield">
        <p>Sobrenome:</p>
        <input
          type="text"
          name="lastName"
          value={contactDetails.lastName}
          onChange={handleInputChange}
          placeholder="Digite o sobrenome"
          required
        />
      </div>
      <div className="addcontact-itemfield">
        <p>E-mail:</p>
        <input
          type="email"
          name="email"
          value={contactDetails.email}
          onChange={handleInputChange}
          placeholder="Digite o e-mail"
          required
        />
      </div>
      <div className="addcontact-phone-numbers">
        <p>Números de Telefone:</p>
        {contactDetails.phoneNumbers.map((phone, index) => (
          <div key={index} className="addcontact-phone-field">
            <input
              type="text"
              value={phone}
              onChange={(e) => handlePhoneNumberChange(index, e.target.value)}
              placeholder="Digite o telefone"
              required={index === 0}
            />
            {contactDetails.phoneNumbers.length > 1 && (
              <button
                type="button"
                onClick={() => removePhoneField(index)}
                className="remove-phone-btn"
              >
                Remover
              </button>
            )}
          </div>
        ))}
        {contactDetails.phoneNumbers.length < 4 && (
          <button
            type="button"
            onClick={addPhoneField}
            className="add-phone-btn"
          >
            Adicionar Número
          </button>
        )}
      </div>
      <button onClick={handleSubmit} className="addcontact-btn">
        Cadastrar Contato
      </button>
    </div>
  );
};

export default AddContact;
