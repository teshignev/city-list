import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import CityList from '../components/CityList';

test('renders component', () => {
  const city = {
    id: 0,
    cityName: 'Tokyo',
    photoUrl: 'url_of_photo'
  }
  render(<CityList cityList={[city]}/>, {wrapper: MemoryRouter});

  const cityName = screen.getByText(/Tokyo/i);
  expect(cityName).toBeInTheDocument();
});