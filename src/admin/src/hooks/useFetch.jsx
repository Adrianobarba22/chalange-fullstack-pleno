import { useState, useEffect } from 'react';

const useFetch = (url) => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    try {
      const response = await fetch(url);
      if (!response.ok) throw new Error('Network response was not ok');
      const result = await response.json();
      setData(result);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  const deleteData = async (id) => {
    try {
      const response = await fetch(`${url}/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) throw new Error('Failed to delete');
      setData(data.filter((item) => item.id !== id));
    } catch (err) {
      setError(err);
    }
  };

  useEffect(() => {
    fetchData();
  }, [url]);

  return { data, loading, error, deleteData };
};

export default useFetch;
