import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import EditCity from '../components/EditCity';

test('renders component', () => {
  const { container } = render(<EditCity />, {wrapper: MemoryRouter});

  const title = screen.getByText(/Edit/i);
  expect(title).toBeInTheDocument();

  const form = container.querySelector('Form');
  expect(form.childElementCount).toBe(3);

  const inputCityName = container.querySelector('Input#cityName');
  expect(inputCityName).toBeInTheDocument();

  const inputPhotoUrl = container.querySelector('Input#photoUrl');
  expect(inputPhotoUrl).toBeInTheDocument();

  const buttons = container.querySelectorAll('Button');
  expect(buttons.length).toBe(2);
});